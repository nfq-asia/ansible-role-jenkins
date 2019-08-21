import jenkins.model.*
import hudson.security.*
import hudson.security.csrf.DefaultCrumbIssuer
import jenkins.security.s2m.AdminWhitelistRule
def instance = jenkins.model.Jenkins.getInstance()
instance.numExecutors = ${number_of_executors}
instance.mode = "${node_mode}"
instance.labelString = "${node_labels}"
println "** Set CSRF Protection"
instance.setCrumbIssuer(new DefaultCrumbIssuer(true))
println "** Set agent to master security"
instance.injector.getInstance(AdminWhitelistRule.class).setMasterKillSwitch(false);
println "** Disable jnlp"
instance.setSlaveAgentPort(-1);
instance.save()
println("** Configuring location properties")
def jenkinsLocationConfiguration = JenkinsLocationConfiguration.get()
jenkinsLocationConfiguration.setAdminAddress("${jenkins_admin_email}")
jenkinsLocationConfiguration.setUrl("${jenkins_url}")
jenkinsLocationConfiguration.save()
