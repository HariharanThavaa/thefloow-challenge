package com.thefloow.challenge.service.impl;

import com.thefloow.challenge.exception.FileReadingException;
import com.thefloow.challenge.service.FileSplitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class FileSplitterServiceImpl implements FileSplitterService {

    private final int mBperSplit;
    private static final String dir = "/tmp/";
    private static final String suffix = ".splitPart";
    static final String ERROR_LOADING_LARGE_FILE = "Error loading large file from path [%s]";

    @Autowired
    FileSplitterServiceImpl(@Value("${part.file.size.inMB}") int mBperSplit) {
        this.mBperSplit = mBperSplit;
    }

    /**
     * Split a file into multiples files.
     * @param fileName   Name of file to be split.
     * @return List of paths
     * @throws FileReadingException
     */

    @Override
    public List<Path> splitFile(String fileName) throws FileReadingException {

        //Clean up temp directory before saving the part files there
        Arrays.stream(new File("/tmp/").listFiles()).forEach(File::delete);
        try{
            return this.splitFile(fileName,mBperSplit);
        }catch (IOException e){
            throw new FileReadingException(String.format(ERROR_LOADING_LARGE_FILE, fileName), e);
        }
    }

    /**
     * Split a file into multiples files.
     *
     * @param fileName   Name of file to be split.
     * @param mBperSplit maximum number of MB per file.
     * @throws IOException
     */
    private List<Path> splitFile(final String fileName, final int mBperSplit) throws IOException {

        List<Path> partFiles = new ArrayList<>();
        final long sourceSize = Files.size(Paths.get(fileName));
        final long bytesPerSplit = 1024L * 1024L * mBperSplit;
        final long numSplits = sourceSize / bytesPerSplit;
        final long remainingBytes = sourceSize % bytesPerSplit;
        int position = 0;

        try (RandomAccessFile sourceFile = new RandomAccessFile(fileName, "r");
             FileChannel sourceChannel = sourceFile.getChannel()) {

            for (; position < numSplits; position++) {
                //write multipart files.
                writePartToFile(bytesPerSplit, position * bytesPerSplit, sourceChannel, partFiles);
            }

            if (remainingBytes > 0) {
                writePartToFile(remainingBytes, position * bytesPerSplit, sourceChannel, partFiles);
            }
        }
        return partFiles;
    }

    private static void writePartToFile(long byteSize, long position, FileChannel sourceChannel, List<Path> partFiles) throws IOException {
        Path fileName = Paths.get(dir + UUID.randomUUID() + suffix);
        try (RandomAccessFile toFile = new RandomAccessFile(fileName.toFile(), "rw");
             FileChannel toChannel = toFile.getChannel()) {
            sourceChannel.position(position);
            toChannel.transferFrom(sourceChannel, 0, byteSize);
        }
        partFiles.add(fileName);
    }
}
