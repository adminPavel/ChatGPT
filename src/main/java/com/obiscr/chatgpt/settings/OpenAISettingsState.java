
package com.obiscr.chatgpt.settings;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

import static com.obiscr.chatgpt.MyToolWindowFactory.*;

/**
 * @author Wuzi
 * Supports storing the application settings in a persistent way.
 * The {@link State} and {@link Storage} annotations define the name of the data and the file name where
 * these persistent application settings are stored.
 */
@State(
        name = "com.obiscr.chatgpt.settings.OpenAISettingsState",
        storages = @Storage("ChatGPTSettingsPlugin.xml")
)
public class OpenAISettingsState implements PersistentStateComponent<OpenAISettingsState> {

  public String customizeUrl = "";
  public SettingConfiguration.SettingURLType urlType =
          SettingConfiguration.SettingURLType.OFFICIAL;
  public String readTimeout = "50000";
  public String connectionTimeout = "50000";

  public String email = "";
  public String password = "";
  public Boolean enableProxy = false;
  public SettingConfiguration.SettingProxyType proxyType =
          SettingConfiguration.SettingProxyType.DIRECT;

  public String proxyHostname = "";
  public String proxyPort = "10000";

  public String accessToken = "";
  public String expireTime = "";
  public String imageUrl = "https://cdn.auth0.com/avatars/me.png";
  public String apiKey = "";
  public Map<Integer,String> contentOrder = new HashMap<>(){{
    put(1, CHATGPT_CONTENT_NAME);
    put(2, GPT35_TRUBO_CONTENT_NAME);
    put(3, ONLINE_CHATGPT_CONTENT_NAME);
  }};

  public Boolean enableLineWarp = true;

  public List<String> customActionsPrefix = new ArrayList<>();
  public static OpenAISettingsState getInstance() {
    return ApplicationManager.getApplication().getService(OpenAISettingsState.class);
  }

  @Nullable
  @Override
  public OpenAISettingsState getState() {
    return this;
  }

  @Override
  public void loadState(@NotNull OpenAISettingsState state) {
    XmlSerializerUtil.copyBean(state, this);
  }

  public void reload() {
    loadState(this);
  }

}