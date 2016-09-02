node {	
	env.PATH = "${tool 'maven-3.3.9'}/bin:${env.PATH}"
	
	stage 'Build'
	checkout scm
	def v = version(readFile('pom.xml'))
	if (v) {
	  echo "Building version ${v}"
	}
	sh 'mvn -Dmaven.test.failure.ignore=true clean org.jacoco:jacoco-maven-plugin:prepare-agent install'	
	// publish JUnit results to Jenkins
    step([$class: 'JUnitResultArchiver', testResults: '**/target/**/TEST-*.xml'])
	
	stage 'Analysis'
	//step <object of type hudson.plugins.sonar.SonarRunnerBuilder>
	sh 'mvn sonar:sonar'
	
	stage 'Deploy'
	sh 'mvn deploy'
	
	stage 'Archive Artifacts'
	step([$class: 'ArtifactArchiver', artifacts: '**/target/**/project-home-generator-*.jar', excludes: null, fingerprint: true, onlyIfSuccessful: true])
}
@NonCPS
def version(text) {
  def matcher = text =~ '<version>(.+)</version>'
  matcher ? matcher[0][1] : null
}
	