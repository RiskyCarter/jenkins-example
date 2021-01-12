import org.kohsuke.github.*

/*
 * This pipeline uses the Jenkins Job DSL plugin to create the multi-branch pipelines
 * for your Github Organization repositories.
 *
 * @see https://wiki.jenkins-ci.org/display/JENKINS/Job+DSL+Plugin
 *
 * It does not consumes 5k requests in 6 minutes, but actually queries the Github API
 * for the repositories in organization and creates the Multi-Branch Pipelines in Jenkins.
 *
 * Usually I will never recommend you executing any pipelines copy-pasted from the Internet.
 * But this one should definitely be an exception of the rule
 * Because the job must be done.
 *
 * @see https://issues.jenkins-ci.org/browse/JENKINS-36121
 *
 * Please read carefully before running
 */


// Redefine these variables for your installation
String folder = 'Tmp'                                               // folder to put your jobs into
String githubLogin = 'john-smith'                                   // github user login
String githubPassword = 'super-duper-password'                      // github user personal access token
String githubOrganization = 'ACME Incorporated'                     // github organization
String scanCredentials = 'scan-github-access'                       // credentials for scanning repository
String checkoutCredentials = 'checkout-github-access'               // credentials for repository checkout
String includes = '*'                                                // What branches to include
String excludes = ''                                                // What branches to exclude
String numToKeep = '5'                                              // Number of recent builds to keep. -1 for all of them
String daysToKeep = '10'                                            // Number of days to keep recent builds. -1 for forever

def buildApp() {
        GitHub github = GitHub.connectUsingPassword(githubLogin, githubPassword)
        rateLimitBefore = github.getRateLimit().remaining
        echo "API requests before: ${rateLimitBefore}"

        // you can say that using .each({ repo -> .... }) would make sense
        // I would say that too.
        // But Jenkins does not agree with us
        // so @see: https://issues.jenkins-ci.org/browse/JENKINS-26481
        List repositories = github.getOrganization(githubOrganization).listRepositories(100).asList()

        for (int i = 0; i < repositories.size(); i++) {
            def repo = repositories[i]
            echo "Scanning repository ${repo.getFullName()}"

            String name = repo.getName()

            // this is called Elvis Operator
            String description = repo.getDescription() ?: ''
        }

}

return this