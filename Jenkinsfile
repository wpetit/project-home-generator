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
	def urlLatestVersion = getLatestVersion()
	dir src/main/docker
	sh 'sudo docker build --env VERSION=${v) --env BUILD=${urlLatestVersion} --env REPOSITORY=maven-snapshot -t project-home-generator .'
	sh 'sudo docker rm -f project-home-generator'
	sh 'sudo docker run -d -p 20000:8080 --name project-home-generator'
	
}
@NonCPS
def version(text) {
  def matcher = text =~ '<version>(.+)</version>'
  matcher ? matcher[0][1] : null
}

@NonCPS
def getLatestVersion() {
  # Repository location
  def server=http://ci.wpetit.com/nexus/repository
  repo=maven-snapshots

  # Maven artifact location
  name=project-home-generator
  artifact=com/wpetit/project-home-generator
  path=$server/$repo/$artifact
  version=`curl -s $path/maven-metadata.xml | grep /version | head -1 | sed "s/.*<version>\([^<]*\)<\/version>.*/\1/"`
  build=`curl -s $path/$version/maven-metadata.xml | grep '<value>' | head -1 | sed "s/.*<value>\([^<]*\)<\/value>.*/\1/"`
  latestVersionBuilt=$name-$build
}
	