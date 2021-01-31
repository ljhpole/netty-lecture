package com.serverimp.rectorimpl;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class ReactorServer implements Runnable {
    final int MAXIN = 1024;
    final int MAXOUT = 1024;
    final Selector selector;
    final ServerSocketChannel serverSocket;
    ReactorServer(int port) throws IOException {
        selector = Selector.open();
        serverSocket = ServerSocketChannel.open();
        serverSocket.socket().bind(
                new InetSocketAddress(port));
        serverSocket.configureBlocking(false);
        SelectionKey sk =
                serverSocket.register(selector,
                        SelectionKey.OP_ACCEPT);
        sk.attach(new Acceptor());
    }
        /*
        Alternatively, use explicit SPI provider:
        SelectorProvider p = SelectorProvider.provider();
        selector = p.openSelector();
        serverSocket = p.openServerSocketChannel();
        */

    // class Reactor continued
    @Override
    public void run() { // normally in a newThread
        try {
            while (!Thread.interrupted()) {
                selector.select();
                Set selected = selector.selectedKeys();
                Iterator it = selected.iterator();
                while (it.hasNext()){
                    dispatch((SelectionKey)(it.next()));
                }
                selected.clear();
            }
        } catch (IOException ex) { /* ... */ }
    }
    void dispatch(SelectionKey k) {
        Runnable r = (Runnable)(k.attachment());
        if (r != null){
            r.run();
        }

    }
    class Acceptor implements Runnable {
        @Override
        public void run() {
            try {
                SocketChannel c = serverSocket.accept();
                if (c != null){
                    new Handler(selector, c);
                }
            }
            catch(IOException ex) { /* ... */ }
        }

    }

    final class Handler implements Runnable {
        final SocketChannel socketChannel;
        final SelectionKey sk;
        ByteBuffer input = ByteBuffer.allocate(MAXIN);
        ByteBuffer output = ByteBuffer.allocate(MAXOUT);
        static final int READING = 0, SENDING = 1;
        int state = READING;
        Handler(Selector sel, SocketChannel c)
                throws IOException {
            socketChannel = c; c.configureBlocking(false);
// Optionally try first read now
            sk = socketChannel.register(sel, 0);
            sk.attach(this);
            sk.interestOps(SelectionKey.OP_READ);
            sel.wakeup();
        }
        boolean inputIsComplete() { /* ... */ return false;}
        boolean outputIsComplete() { /* ... */ return false;}
        void process() { /* ... */ }
        // class Handler continued
        @Override
        public void run() {
            try {
                /*
                socket.read(input);
if (inputIsComplete()) {
process();
sk.attach(new Sender());
sk.interest(SelectionKey.OP_WRITE);
sk.selector().wakeup();
}

class Sender implements Runnable {
public void run(){ // ...
socket.write(output);
if (outputIsComplete()) sk.cancel();
}
}
                 */
                if (state == READING) {
                    read();
                }
                else if (state == SENDING){
                    send();
                }
            } catch (IOException ex) { /* ... */ }
        }
        void read() throws IOException {
            socketChannel.read(input);
            if (inputIsComplete()) {
                process();
                state = SENDING;
// Normally also do first write now
                sk.interestOps(SelectionKey.OP_WRITE);
            }
        }
        void send() throws IOException {
            socketChannel.write(output);
            if (outputIsComplete()){
                sk.cancel();
            }
        }
    }
}
