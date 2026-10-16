# Balde de Água Realista (Fabric, Minecraft 26.3)

## Como compilar
1. JDK 25 instalado.
2. Copie `gradlew`, `gradlew.bat` e a pasta `gradle/` do repositório oficial `FabricMC/fabric-example-mod`
   (e confira o `loom_version` de lá no `gradle.properties`).
3. `./gradlew build`  ->  jar em `build/libs/`
4. Teste: `./gradlew runClient` (ponha o Sodium 26.3 em `run/mods/`).

## Como usar
- Receita: Balde de Água + Diamante (sem forma).
- Ou: `/give @s realwater:realistic_water_bucket`
- Clique direito para despejar. O balde continua cheio; a água NÃO vira fonte infinita.
- Balde vazio vanilla recolhe a fonte (devolve o balde realista).
