package ru.ancap.framework.api.plugin.plugins;

import java.io.InputStream;

public interface ResourceSource {

    InputStream getResource(String fileName);
}
