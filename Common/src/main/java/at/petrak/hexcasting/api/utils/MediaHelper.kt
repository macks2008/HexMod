@file:JvmName("MediaHelper")

package at.petrak.hexcasting.api.utils

import at.petrak.hexcasting.api.addldata.ADMediaHolder
import at.petrak.hexcasting.xplat.IXplatAbstractions
import net.minecraft.util.Mth
import net.minecraft.world.item.ItemStack
import kotlin.math.roundToInt

fun isMediaItem(stack: ItemStack): Boolean {
    val manaHolder = IXplatAbstractions.INSTANCE.findManaHolder(stack) ?: return false
    if (!manaHolder.canProvide())
        return false
    return manaHolder.withdrawMedia(-1, true) > 0
}

/**
 * Extract [cost] mana from [stack]. If [cost] is less than zero, extract all mana instead.
 * This may mutate [stack] (and may consume it) unless [simulate] is set.
 *
 * If [drainForBatteries] is false, this will only consider forms of mana that can be used to make new batteries.
 *
 * Return the amount of mana extracted. This may be over [cost] if mana is wasted.
 */
@JvmOverloads
fun extractMedia(
    stack: ItemStack,
    cost: Int = -1,
    drainForBatteries: Boolean = false,
    simulate: Boolean = false
): Int {
    val manaHolder = IXplatAbstractions.INSTANCE.findManaHolder(stack) ?: return 0

    return extractMedia(manaHolder, cost, drainForBatteries, simulate)
}

/**
 * Extract [cost] mana from [holder]. If [cost] is less than zero, extract all mana instead.
 * This may mutate the stack underlying [holder] (and may consume it) unless [simulate] is set.
 *
 * If [drainForBatteries] is false, this will only consider forms of mana that can be used to make new batteries.
 *
 * Return the amount of mana extracted. This may be over [cost] if mana is wasted.
 */
fun extractMedia(
    holder: ADMediaHolder,
    cost: Int = -1,
    drainForBatteries: Boolean = false,
    simulate: Boolean = false
): Int {
    if (drainForBatteries && !holder.canConstructBattery())
        return 0

    return holder.withdrawMedia(cost, simulate)
}

/**
 * Sorted from least important to most important
 */
fun compareMediaItem(aMana: ADMediaHolder, bMana: ADMediaHolder): Int {
    val priority = aMana.consumptionPriority - bMana.consumptionPriority
    if (priority != 0)
        return priority

    return aMana.withdrawMedia(-1, true) - bMana.withdrawMedia(-1, true)
}

fun mediaBarColor(mana: Int, maxMana: Int): Int {
    val amt = if (maxMana == 0) {
        0f
    } else {
        mana.toFloat() / maxMana.toFloat()
    }

    val r = Mth.lerp(amt, 84f, 254f)
    val g = Mth.lerp(amt, 57f, 203f)
    val b = Mth.lerp(amt, 138f, 230f)
    return Mth.color(r / 255f, g / 255f, b / 255f)
}

fun mediaBarWidth(mana: Int, maxMana: Int): Int {
    val amt = if (maxMana == 0) {
        0f
    } else {
        mana.toFloat() / maxMana.toFloat()
    }
    return (13f * amt).roundToInt()
}