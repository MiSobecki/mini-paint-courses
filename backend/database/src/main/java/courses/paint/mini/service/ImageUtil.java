package courses.paint.mini.service;

import java.io.ByteArrayOutputStream;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class ImageUtil {

    public static byte[] compressImage(byte[] data) {
        var deflater = new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(data);
        deflater.finish();

        var outputStream = new ByteArrayOutputStream(data.length);
        var tmp = new byte[4 * 1024];
        while (!deflater.finished()) {
            var size = deflater.deflate(tmp);
            outputStream.write(tmp, 0, size);
        }
        try {
            outputStream.close();
        } catch (Exception ignored) {
        }
        return outputStream.toByteArray();
    }


    public static byte[] decompressImage(byte[] data) {
        var inflater = new Inflater();
        inflater.setInput(data);
        var outputStream = new ByteArrayOutputStream(data.length);
        var tmp = new byte[4 * 1024];
        try {
            while (!inflater.finished()) {
                var count = inflater.inflate(tmp);
                outputStream.write(tmp, 0, count);
            }
            outputStream.close();
        } catch (Exception ignored) {
        }
        return outputStream.toByteArray();
    }

}
