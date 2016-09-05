node {	
	env.PATH = "${tool 'maven-3.3.9'}/bin:${env.PATH}"
	
	stage 'Checkout'
    checkout scm
    def v = version(readFile('pom.xml'))
	if (v) {
	  echo "Building version ${v}"
	}
    
    stage 'Build'
    sh "mvn clean package"
    
    stage 'Unit-Tests'
    sh "mvn org.jacoco:jacoco-maven-plugin:prepare-agent test"
    step([
            $class     : 'JUnitResultArchiver',
            testResults: '**/target/**/surefire-reports/TEST-*.xml'
    ])
    
    stage 'Integration-Tests'
    sh "mvn org.jacoco:jacoco-maven-plugin:prepare-agent-integration verify"

    step([
            $class     : 'ArtifactArchiver',
            artifacts  : '**/target/*.jar',
            fingerprint: true
    ])
    step([
            $class     : 'JUnitResultArchiver',
            testResults: '**/target/**/failsafe-reports/TEST*.xml'
    ])
	
	stage 'Analysis'
	sh 'mvn sonar:sonar'
	
	stage 'Deploy'
	sh 'mvn deploy'
	sh 'cp target/*.jar src/main/docker'
	dir('src/main/docker') {
		sh 'sudo docker build project-home-generator .'
		sh 'sudo docker rm -f project-home-generator'
		sh 'sudo docker run -d -p 20000:8080 --name project-home-generator'
	}
	
}
@NonCPS
def version(text) {
  def matcher = text =~ '<version>(.+)</version>'
  matcher ? matcher[0][1] : null
}

	