package com.example.lr2.plugins;

public interface Plugin {

    String getExtension();

    String getDescription();

    byte[] encode(byte[] data);

    byte[] decode(byte[] data);
}
