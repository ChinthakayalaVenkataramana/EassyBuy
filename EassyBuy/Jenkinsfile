pipeline {

    agent any

    stages {

        stage('Build') {
            steps {
                dir('EassyBuy') {
                    sh 'mvn clean package -DskipTests'
                }
            }
        }

     stage('Test') {
    steps {
        dir('EassyBuy') {
            withEnv([
                'DB_URL=jdbc:mysql://localhost:3306/mysqlking',
                'DB_USERNAME=eassybuy',
                'DB_PASSWORD=EassyBuy@12345'
            ]) {
                sh 'mvn test'
            }
        }
    }
}

        stage('Docker Build') {
            steps {
                dir('EassyBuy') {
                    sh 'docker build -t eassybuy:latest .'
                }
            }
        }

        stage('Deploy') {
            steps {
                sh '''
                    echo "Stopping old EassyBuy container..."

                    docker stop EassyBuy || true
                    docker rm EassyBuy || true

                    echo "Starting new EassyBuy container..."

                    docker run -d \
                      --name EassyBuy \
                      --add-host=host.docker.internal:host-gateway \
                      -p 8090:8090 \
                      -e DB_URL="jdbc:mysql://host.docker.internal:3306/mysqlking" \
                      -e DB_USERNAME="eassybuy" \
                      -e DB_PASSWORD="EassyBuy@12345" \
                      eassybuy:latest

                    echo "Waiting for application to start..."
                    sleep 15

                    echo "Container status:"
                    docker ps -a --filter "name=EassyBuy"

                    if [ "$(docker inspect -f '{{.State.Running}}' EassyBuy)" != "true" ]; then
                        echo "Container failed to start!"
                        echo "Container logs:"
                        docker logs EassyBuy
                        exit 1
                    fi

                    echo "Container is running successfully!"
                '''
            }
        }
    }

    post {
        success {
            echo 'Build, Test, Docker Build and Deployment completed successfully!'
        }

        failure {
            echo 'Pipeline failed. Check the console output.'
        }
    }
}