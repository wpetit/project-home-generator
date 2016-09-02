node {	
	env.PATH = "${tool 'maven-3.3.9'}/bin:${env.PATH}"
	
	stage 'Build'
	checkout scm
	sh 'mvn -Dmaven.test.failure.ignore=true clean org.jacoco:jacoco-maven-plugin:prepare-agent install'	
	// publish JUnit results to Jenkins
    step([$class: 'JUnitResultArchiver', testResults: '**/target/**/TEST-*.xml'])
	
	stage 'Analysis'
	//step <object of type hudson.plugins.sonar.SonarRunnerBuilder>
	sh 'mvn sonar:sonar'
	
	stage 'Deploy Integration'
	input 'Ready to go?'
	sh 'mvn cargo:start &'
	sh 'sleep 60'
	sh 'mvn cargo:stop &'
	
	stage 'Archive Artifacts'
	step([$class: 'ArtifactArchiver', artifacts: '**/target/**/simple-maven-project.war', excludes: null, fingerprint: true, onlyIfSuccessful: true])
}

	