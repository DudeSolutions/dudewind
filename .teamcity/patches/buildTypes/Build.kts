package patches.buildTypes

import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.PowerShellStep
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.powerShell
import jetbrains.buildServer.configs.kotlin.v2018_1.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, change the buildType with id = 'Build'
accordingly, and delete the patch script.
*/
changeBuildType(RelativeId("Build")) {
    check(name == "Build") {
        "Unexpected name: '$name'"
    }
    name = "Build Test Publish"

    vcs {

        check(checkoutMode == CheckoutMode.AUTO) {
            "Unexpected option value: checkoutMode = $checkoutMode"
        }
        checkoutMode = CheckoutMode.ON_AGENT
    }

    expectSteps {
    }
    steps {
        insert(0) {
            powerShell {
                name = "Setup Git Config"
                edition = PowerShellStep.Edition.Desktop
                scriptMode = script {
                    content = """
                        ${'$'}env:path += ";" + (Get-Item "Env:ProgramFiles").Value + "\Git\bin"
                        
                        git config --global push.default simple
                        git config --global user.email "team.city@dudesoln.com"
                        git config --global user.name "Team City"
                    """.trimIndent()
                }
            }
        }
        insert(1) {
            powerShell {
                name = "NPM Install"
                scriptMode = script {
                    content = "npm install"
                }
            }
        }
        insert(2) {
            powerShell {
                name = "NPM Test"
                scriptMode = script {
                    content = "npm test"
                }
            }
        }
        insert(3) {
            powerShell {
                name = "NPM Test (1)"
                scriptMode = script {
                    content = "npm test"
                }
            }
        }
    }
}
