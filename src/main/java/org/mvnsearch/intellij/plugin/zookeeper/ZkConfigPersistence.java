package org.mvnsearch.intellij.plugin.zookeeper;

import com.intellij.openapi.components.*;
import com.intellij.openapi.project.Project;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.generate.tostring.util.StringUtil;

/**
 * zookeeper configuration persistence
 *
 * @author linux_china
 */
@State(name = "ZooKeeperConfig", storages = {@Storage(id = "main", file = StoragePathMacros.PROJECT_CONFIG_DIR + "/zookeeper_config.xml")})
public class ZkConfigPersistence implements PersistentStateComponent<ZkConfigPersistence> {
    public String host;
    public Integer port;
    public boolean enabled;

    public static ZkConfigPersistence getInstance(Project project) {
        return ServiceManager.getService(project, ZkConfigPersistence.class);
    }

    @Nullable
    public ZkConfigPersistence getState() {
        return this;
    }

    public void loadState(ZkConfigPersistence state) {
        XmlSerializerUtil.copyBean(state, this);
    }

    public boolean isAvailable() {
        return enabled && StringUtil.isNotEmpty(host);
    }

    public String getUrl() {
        return host + ":" + port;
    }
}
