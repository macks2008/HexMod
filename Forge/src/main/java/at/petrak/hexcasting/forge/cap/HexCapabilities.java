package at.petrak.hexcasting.forge.cap;

import at.petrak.hexcasting.api.addldata.*;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;

public final class HexCapabilities {

    public static final Capability<ADMediaHolder> MEDIA = CapabilityManager.get(new CapabilityToken<>() {
    });
    public static final Capability<ADIotaHolder> IOTA = CapabilityManager.get(new CapabilityToken<>() {
    });
    public static final Capability<ADHexHolder> STORED_HEX = CapabilityManager.get(new CapabilityToken<>() {
    });
    public static final Capability<ADVariantItem> VARIANT_ITEM = CapabilityManager.get(new CapabilityToken<>() {
    });
    public static final Capability<ADPigment> COLOR = CapabilityManager.get(new CapabilityToken<>() {
    });
}
