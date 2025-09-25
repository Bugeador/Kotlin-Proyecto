
//Matias Jaque
//Jose Perez


fun main() {
    val repo = Repositorio()
    repo.afps.addAll(listOf(
        AFP("AFP Habitat", 0.11),
        AFP("AFP Provida", 0.10),
        AFP("AFP Modelo", 0.09)
    ))

    while (true) {
        println("\n----- MENÚ PRINCIPAL -----")
        println("1. Lista empleados")
        println("2. Agregar empleados")
        println("3. Generar liquidacion por rut")
        println("4. Listar liquidaciones")
        println("5. Flitrar empleados por AFP")
        println("6. Eliminar empleados")
        println("0. Salir")
        print("Seleccione una opcion: ")

        when (readln().toInt()) {
            1 -> listarEmpleados(repo)
            2 -> agregarEmpleado(repo)
            3 -> generarLiquidacion(repo)
            4 -> listarLiquidaciones(repo)
            5 -> filtrarEmpleadosPorAFP(repo)
            6 -> eliminarEmpleado(repo)
            0 -> return
            else -> println("Opción inválida")
        }
    }
}

/*----------FUNCION PARA VERIFICAR EMPLEADOS----------*/
fun listarEmpleados(repo: Repositorio) {
    if (repo.empleados.isEmpty()) {
        println("No hay empleados registrados.")
    } else {
        repo.empleados.forEach { println(it) }
    }
}

/*----------FUNCION PARA REGISTRAR EMPLEADOS----------*/
fun agregarEmpleado(repo: Repositorio) {
    print("Ingrese RUT: ")
    val rut = readln()
    print("Ingrese nombre: ")
    val nombre = readln()
    print("Ingrese sueldo bruto: ")
    val sueldo = readln().toDouble()
    print("Ingrese calle: ")
    val calle = readln()
    print("Ingrese número: ")
    val numero = readln().toInt()
    print("Ingrese ciudad: ")
    val ciudad = readln()
    println("Seleccione AFP:")
    repo.afps.forEachIndexed { index, afp -> println("${index + 1}. ${afp.nombre}") }
    val afpIndex = readln().toInt() - 1
    val empleado = Empleado(rut, nombre, sueldo, Direccion(calle, numero, ciudad), repo.afps[afpIndex])
    repo.empleados.add(empleado)
    println("Empleado agregado correctamente.")
}

/*----------FUNCION PARA APLICAR LIQUIDACION----------*/
fun generarLiquidacion(repo: Repositorio) {
    print("Ingrese RUT del empleado: ")
    val rut = readln()
    val empleado = repo.empleados.find { it.rut == rut }
    if (empleado != null) {
        val liquidacion = LiquidacionSueldo.generar(empleado)
        repo.liquidaciones.add(liquidacion)
        println("Liquidación generada: ${liquidacion}")
    } else {
        println("Empleado no encontrado.")
    }
}

/*----------FUNCION PARA GUARDAR LIQUIDACIONES----------*/
fun listarLiquidaciones(repo: Repositorio) {
    if (repo.liquidaciones.isEmpty()) {
        println("No hay liquidaciones generadas.")
    } else {
        repo.liquidaciones.forEach { println(it) }
    }
}

/*----------FUNCION PARA FILTRAR EMPLEADOS POR AFP----------*/
fun filtrarEmpleadosPorAFP(repo: Repositorio) {
    println("Seleccione AFP para filtrar:")
    repo.afps.forEachIndexed { index, afp -> println("${index + 1}. ${afp.nombre}") }
    val afpIndex = readln().toInt() - 1
    val afpSeleccionada = repo.afps[afpIndex]
    val filtrados = repo.empleados.filter { it.afp == afpSeleccionada }
        .sortedByDescending { it.sueldo }
    if (filtrados.isEmpty()) {
        println("No hay empleados en esta AFP.")
    } else {
        filtrados.forEach { println(it) }
    }
}

/*----------FUNCION PARA ELIMINAR EMPLEADOS----------*/
fun eliminarEmpleado(repo: Repositorio) {
    print("Ingrese RUT del empleado a eliminar: ")
    val rut = readln()
    val eliminado = repo.empleados.removeIf { it.rut == rut }
    if (eliminado) {
        println("Empleado eliminado.")
    } else {
        println("Empleado no encontrado.")
    }
}
