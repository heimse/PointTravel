# PointTravel

**PointTravel** — это плагин для серверов Minecraft (Spigot) версии **1.21.4**, который позволяет игрокам создавать и управлять множеством точек для телепортации. Плагин предоставляет следующие команды:

- **/setpoint <имя>** – устанавливает точку с указанным именем, сохраняя текущую локацию игрока.
- **/goto <имя>** – телепортирует игрока к ранее установленной точке.
- **/listpoints** – выводит список всех точек, созданных игроком.

## Требования

- **Minecraft сервер:** Spigot 1.21.4 (или совместимый серверный софт, например, Paper)
- **Java:** OpenJDK 21 (рекомендуется для версий Minecraft от 1.20.6 и выше)
- **Система сборки:** Maven
- **IDE:** IntelliJ IDEA (рекомендуется, но не обязательно)

## Установка и сборка

1. **Клонируйте репозиторий или создайте Maven-проект**  
   Если вы начинаете с нуля, создайте новый Maven-проект в IntelliJ IDEA с группой `com.example.pointtravel` и артефактом `PointTravel`.

2. **Настройте файл pom.xml**  
   Добавьте зависимость на Spigot API. Пример минимальной конфигурации:

   ```xml
   <project ...>
       <modelVersion>4.0.0</modelVersion>
       <groupId>com.example.pointtravel</groupId>
       <artifactId>PointTravel</artifactId>
       <version>1.0</version>
       <packaging>jar</packaging>

       <properties>
           <spigot.version>1.21.4-R0.1-SNAPSHOT</spigot.version>
           <maven.compiler.source>21</maven.compiler.source>
           <maven.compiler.target>21</maven.compiler.target>
       </properties>

       <dependencies>
           <dependency>
               <groupId>org.spigotmc</groupId>
               <artifactId>spigot-api</artifactId>
               <version>${spigot.version}</version>
               <scope>provided</scope>
           </dependency>
       </dependencies>

       <repositories>
           <repository>
               <id>spigot-repo</id>
               <url>https://hub.spigotmc.org/nexus/content/repositories/snapshots/</url>
           </repository>
       </repositories>
   </project>
