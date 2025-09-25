
data class LiquidacionSueldo (
    val empleado: Empleado,
    val descuentoAFP: Double,
    val descuentoSalud: Double,
    val descuentoCesantia: Double,
    val sueldoLiquido: Double,
) {
    companion object {
        fun generar(empleado: Empleado): LiquidacionSueldo {
            val descAFP = empleado.sueldo * empleado.afp.tasa
            val descSalud = empleado.sueldo * 0.07
            val descCesantia = empleado.sueldo * 0.006
            val liquido = empleado.sueldo - (descAFP + descSalud + descCesantia)

            return LiquidacionSueldo(empleado, descAFP, descSalud, descCesantia, liquido)
        }
    }
}