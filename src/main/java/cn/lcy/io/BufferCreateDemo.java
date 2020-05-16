package cn.lcy.io;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.Set;
import java.util.TreeSet;

public class BufferCreateDemo {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        byte[] bytes = new byte[10];
        ByteBuffer wrap = ByteBuffer.wrap(bytes);
        ByteBuffer wrapOffer = ByteBuffer.wrap(bytes,2,5);
        print(byteBuffer,wrap,wrapOffer);
    }
    private static void print(Buffer... buffers){
        Set<String> s = new TreeSet<>();

        for (Buffer buffer:buffers){
            System.out.println("capacity="+buffer.capacity()
                    +",limit="+buffer.limit()
            +",positiont="+buffer.position()
            +",hasRemaining="+buffer.hasRemaining()
            +",remaining="+buffer.remaining()
            +",hasArray="+buffer.hasArray()
            +"isReadOnly="+buffer.isReadOnly()
            +".arrayOfferset="+buffer.arrayOffset());
        }
    }
}
