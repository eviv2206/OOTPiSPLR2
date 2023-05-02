package com.example.lr2.plugins.impl;

import com.example.lr2.plugins.Plugin;
import org.apache.commons.codec.binary.Base64;

public class Base64Plugin implements Plugin {

    @Override
    public String getExtension() {
        return "*.base64";
    }

    @Override
    public String getDescription() {
        return "Base64 coding";
    }

    @Override
    public byte[] encode(byte[] data) {
        return Base64.encodeBase64(data);
    }

    @Override
    public byte[] decode(byte[] data) {
        return Base64.decodeBase64(data);
    }

    @Override
    public String toString() {
        return "base64";
    }
}
