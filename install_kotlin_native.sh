curl -OL https://github.com/JetBrains/kotlin-native/releases/download/v0.8.1/kotlin-native-linux-0.8.1.tar.gz
tar xf kotlin-native-linux-0.8.1.tar.gz
kotlin-native-linux-0.8.1/bin/kotlinc iamnative.kt -o iamnative_travis
./iamnative_travis.kexe
chmod +x ./iamnative_travis.kexe
chmod u+x ./iamnative_travis.kexe
ls
