package com.anmnight.imageloader;

public class HexNameGenerate {
    public String generate(String path) {
        return String.valueOf(path.hashCode());
    }
}
