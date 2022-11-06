package at.petrak.hexcasting.common.casting.operators

import at.petrak.hexcasting.api.spell.ConstManaAction
import at.petrak.hexcasting.api.spell.asActionResult
import at.petrak.hexcasting.api.spell.casting.CastingContext
import at.petrak.hexcasting.api.spell.getEntity
import at.petrak.hexcasting.api.spell.iota.Iota
import at.petrak.hexcasting.xplat.IXplatAbstractions

object OpTheCoolerReadable : ConstManaAction {
    override val argc = 1

    override fun execute(
        args: List<Iota>,
        ctx: CastingContext
    ): List<Iota> {
        val target = args.getEntity(0, argc)
        ctx.assertEntityInRange(target)

        val datumHolder = IXplatAbstractions.INSTANCE.findDataHolder(target)
            ?: return false.asActionResult

        datumHolder.readIota(ctx.world)
            ?: datumHolder.emptyIota()
            ?: return false.asActionResult

        return true.asActionResult
    }
}
