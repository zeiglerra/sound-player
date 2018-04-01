package com.example.rzeigler3.soundrecorder.utils;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Created by rzeigler3 on 3/18/2018.
 */

public class MyFilenameFilter implements FilenameFilter {

    private final String ext;

    public MyFilenameFilter(String ext) {
        this.ext = ext.toLowerCase();
    }

    @Override
    public boolean accept(File dir, String name) {
        return name.toLowerCase().endsWith(ext);
    }

}
