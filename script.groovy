import org.kohsuke.github.*

int rateLimitBefore = 0
String githubLogin = 'riskycarter'                                   // github user login
String githubPassword = 'Newsza2540.'                      // github user personal access token
def buildApp() {
    echo 'buildApp called..'
    GitHub github = GitHub.connectUsingPassword(githubLogin, githubPassword)
    rateLimitBefore = github.getRateLimit().remaining
    echo "API requests before: ${rateLimitBefore}"

    // you can say that using .each({ repo -> .... }) would make sense
    // I would say that too.
    // But Jenkins does not agree with us
    // so @see: https://issues.jenkins-ci.org/browse/JENKINS-26481
    List repositories = github.getOrganization(githubOrganization).listRepositories(100).asList()
    echo "repository size: ${repositories.size()}"
}

return this