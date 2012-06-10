-- How to use with Eclipse --

(1) Create a new Java project.
(2) Drag all the files into the project folder.
(5) Click on File -> Refresh.
(4) Go to File -> Properties -> Java Build Path -> Libraries.
(5) Click Add JARs... and add all the jars in the lib/jars folder.
(5) Expand lwjgl.jar.
(6) Double-click Native library location.
(7) Fill in $workspace_name$/lib/natives-(win/mac).

-- How to use with NetBeans --

(1) Create a new Java Application.
(2) Drag all the files into the project folder.
(3) Go to Project Properties (right-click on your project) -> Libraries.
(4) Click Add JAR/Folder and add all the jars in the lib/jars folder.
(5) Go to Project Properties -> Run.
(6) Enter -Djava.library.path="lib/natives-(win/mac)" as VM Options.  

-- How to use with IntelliJ IDEA --

(1) Create a new project.
(2) Drag all the files into the project folder.
(3) Click on File -> Project Structure -> Modules -> Dependencies.
(4) Click the plus icon and select Jars or directories... .
(5) Add all the jars in the lib/jars folder.
(6) Go to Run -> Edit Configurations... -> Defaults -> Application.
(7) Enter -Djava.library.path="lib/natives-(win/mac)" as VM Options.  


