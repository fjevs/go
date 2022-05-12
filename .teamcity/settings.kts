import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.triggers.vcs

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2022.04"

project {

    buildType(name: "echo")
	buildType(huecho)
	buildType(firstp)
	buildType(secondp)
	
	sequential {
		buildType(name: "echo")
		parallel {
			buildType(firstp)
			buildType(secondp)
		}
		buildType(huecho)
	}
}

class Hueven(name: String) : BuildType({
    this.name = name

    params {
        param("asd", "5")
    }

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        script {
			val stepName = "kxe"
            name = stepName
            scriptContent = "echo %asd%"
        }
    }
})

object huecho : BuildType({
    name = "huecho"

    params {
        param("zxc", "9")
    }

    vcs {
        root(DslContext.settingsRoot)
    }
	
/* 	dependencies {
		snapshot(Echo) {}
	} */

    steps {
        script {
			val stepName = "fff"
            name = stepName
            scriptContent = "echo %zxc%"
        }
    }

})

object firstp : BuildType({
    name = "firstp"

    params {
        param("asd", "5")
    }

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        script {
			val stepName = "kxe"
            name = stepName
            scriptContent = "echo %asd%"
        }
    }

})

object secondp : BuildType({
    name = "secondp"

    params {
        param("asd", "5")
    }

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        script {
			val stepName = "kxe"
            name = stepName
            scriptContent = "echo %asd%"
        }
    }

})