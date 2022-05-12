import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.projectFeatures.buildReportTab
import jetbrains.buildServer.configs.kotlin.vcs.GitVcsRoot

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
    description = "Contains all other projects"

    features {
        buildReportTab {
            id = "PROJECT_EXT_1"
            title = "Code Coverage"
            startPage = "coverage.zip!index.html"
        }
    }

    cleanup {
        baseRule {
            preventDependencyCleanup = false
        }
    }

    subProject(Hue)
    subProject(Hue1)
}


object Hue : Project({
    name = "hue"

    buildType(Hue_Echo)
})

object Hue_Echo : BuildType({
    name = "echo"

    steps {
        script {
            scriptContent = "echo hue"
        }
    }
})


object Hue1 : Project({
    name = "Hue (1)"

    vcsRoot(Hue1_HttpsGithubComFjevsHueRefsHeadsMain)

    buildType(Hue1_Build)
})

object Hue1_Build : BuildType({
    name = "Build"

    vcs {
        root(Hue1_HttpsGithubComFjevsHueRefsHeadsMain)
    }

    steps {
        script {
            scriptContent = """
                chcp 65001
                echo hue
            """.trimIndent()
        }
    }
})

object Hue1_HttpsGithubComFjevsHueRefsHeadsMain : GitVcsRoot({
    name = "https://github.com/fjevs/hue#refs/heads/main"
    url = "https://github.com/fjevs/hue"
    branch = "refs/heads/main"
    branchSpec = "refs/heads/*"
    authMethod = password {
        userName = "fjevs"
        password = "credentialsJSON:258468fa-1bf1-4a50-b5ed-536e196ae4b0"
    }
})
