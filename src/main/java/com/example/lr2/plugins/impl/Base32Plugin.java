package com.example.lr2.plugins.impl;

import com.example.lr2.plugins.Plugin;
import org.apache.commons.codec.binary.Base32;

public class Base32Plugin implements Plugin {
    @Override
    public String getExtension() {
        return "*.base32";
    }

    @Override
    public String getDescription() {
        return "Base32 coding";
    }

    @Override
    public byte[] encode(byte[] data) {
        return new Base32().encode(data);
    }

    @Override
    public byte[] decode(byte[] data) {
        return new Base32().decode(data);
    }

    @Override
    public String toString() {
        return "base32";
    }
}
