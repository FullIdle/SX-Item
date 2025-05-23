package github.saukiya.sxitem.util;

import github.saukiya.sxitem.SXItem;
import lombok.Getter;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.text.MessageFormat;
import java.util.Collections;
import java.util.List;

public enum Message {

    GIVE__NO_ITEM,
    GIVE__GIVE_ITEM,
    GIVE__GIVE_ITEM_ERROR,

    SAVE__HAS_ITEM,
    SAVE__SAVE_ITEM,
    SAVE__SAVE_NO_TYPE,
    SAVE__SAVE_ITEM_ERROR,

    NBT__CLICK_COPY,

    SCRIPT__NULL_FILE,
    SCRIPT__INVOKE_RESULT,
    SCRIPT__INVOKE_FAIL,

    ADMIN__NO_PERMISSION_CMD,
    ADMIN__NO_CMD,
    ADMIN__NO_FORMAT,
    ADMIN__NO_ONLINE,
    ADMIN__PLUGIN_RELOAD;

    @Getter
    private static YamlConfiguration messages;

    /**
     * 获取String
     *
     * @param args Object...
     * @return String
     */
    public String get(Object... args) {
        return getString(toString(), args);
    }

    /**
     * 获取List
     *
     * @param args Object...
     * @return List
     */
    public List<String> getList(Object... args) {
        return getStringList(toString(), args);
    }

    @Override
    public String toString() {
        return name().replace("__", ".");
    }

    /**
     * 加载Message类
     */
    public static void loadMessage() {
        File file = new File(SXItem.getInst().getDataFolder(), "Message.yml");
        if (!file.exists()) {
            SXItem.getInst().getLogger().warning("File is not exists: Message.yml");
            messages = new YamlConfiguration();
            return;
        }
        messages = YamlConfiguration.loadConfiguration(file);
    }

    public static String getString(String loc, Object... args) {
        return MessageFormat.format(messages.getString(loc, "Null Message: " + loc), args).replace('&', '§');
    }

    public static List<String> getStringList(String loc, Object... args) {
        List<String> list = messages.getStringList(loc);
        if (list.isEmpty()) return Collections.singletonList("Null Message: " + loc);
        list.replaceAll(str -> MessageFormat.format(str, args).replace('&', '§'));
        return list;
    }
}