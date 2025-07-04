### IDEMPIERE-REST  NOTES

#### COMPILING WITH MAVEN.

Use maven property maven.repo.local:

$ mvn -Dmaven.repo.local=$HOME/.my/other/repository clean install

No modifications to settings.xml are necessary.

$ mvn -Dmaven.repo.local=$HOME/.m2/repository_11_OK clean install


#### INSTALLING.

sudo ./update-rest-extensions.sh file:///home/luisamesty/sources/iDempiere11/idempiere-rest/com.trekglobal.idempiere.extensions.p2/target/repository/
Normal Install using Apache Felix Web Console

*** IMPORTANT ***
Select Start Level 4

OSGI CONSOLE
telnet localhost 12612

#### PLUGIN OSGI 

*** IMPORTANT **

Eclipse plugin org.eclipse.osgi.services is required for API rest

Maven Repo: 
https://mvnrepository.com/artifact/org.eclipse.platform/org.eclipse.osgi.services

Idempiere Version 12 uses:
<unit id="org.eclipse.osgi.services" version="3.11.100.v20221006-1531"/>

Install plugin using Equinox OSGI:
org.eclipse.osgi.services-3.11.100.v20221006-1531.jar



