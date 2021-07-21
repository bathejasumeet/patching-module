package com.patch.gzip;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GZIPTest {

    public static void test() {
        try {
            byte[] part1 = zip(stream("hello ".getBytes()));
            byte[] part2 = zip(stream("world".getBytes()));

            InputStream zippedData = new SequenceInputStream(stream(part1), stream(part2));
            byte[] data = unzip(zippedData);

            System.out.println(new String(data));
        }
        catch (IOException x) {
            x.printStackTrace();
        }
    }

    private static InputStream stream(byte[] data) {
        return new ByteArrayInputStream(data);
    }

    private static byte[] zip(InputStream data) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream zout = new GZIPOutputStream(out);
        copy(data, zout);
        data.close();
        zout.close();
        return out.toByteArray();
    }

    private static byte[] unzip(InputStream zippedData) throws IOException {
        GZIPInputStream zin = new GZIPInputStream(zippedData);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        copy(zin, out);
        zin.close();
        out.close();
        return out.toByteArray();
    }

    private static void copy(InputStream source, OutputStream sink) throws IOException {
        byte[] buf = new byte[4096];
        int n;
        while ((n = source.read(buf)) > 0) {
            sink.write(buf, 0, n);
        }
    }

}