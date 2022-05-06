package ru.ancap.framework.plugin.api.plugins;

import java.io.InputStream;

public interface ResourceSource {

    InputStream getResource(String fileName);
}
