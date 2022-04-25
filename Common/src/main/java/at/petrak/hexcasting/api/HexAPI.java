package at.petrak.hexcasting.api;

import com.google.common.base.Suppliers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Supplier;

public interface HexAPI {
    String MOD_ID = "hexcasting";
    Logger LOGGER = LogManager.getLogger(MOD_ID);

    Supplier<HexAPI> INSTANCE = Suppliers.memoize(() -> {
        try {
            return (HexAPI) Class.forName("at.petrak.hexcasting.common.impl.BotaniaAPIImpl")
                .getDeclaredConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            LogManager.getLogger().warn("Unable to find HexAPIImpl, using a dummy");
            return new HexAPI() {
            };
        }
    });

    static HexAPI instance() {
        return INSTANCE.get();
    }
}
