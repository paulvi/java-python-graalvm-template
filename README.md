
Run Python code that is using packages like `requests` from Java,
using GraalVM with Python module (`graalpython`).

This template is based on https://github.com/hpi-swa-lab/graalpython-java-example

Ready for production!

# To use

    git clone https://github.com/paulvi/graalpython-java-template
    mvn generate-resources

# Deploy to production

Build:

    mvn package

Distribute:  
copy target/your.jar, pom.xml, req.txt to some new location   

    mkdir dist
    cp ./{target/*.jar,pom.xml,req.txt} dist/

Run:

    cd dist
    mvn generate-resources
    java -jar graalpython-java.jar


# To recreate

This project is created as Python project (using `graalpython`),
then converted into Java project by adding maven pom.xml.

Create new graalpython project:

    mkdir dirname && cd dirname
    graalpython -m venv venv
    source venv/bin/activate

To see list of supported packages:

    graalpython -m ginstall install --help

Install `requests`

    graalpython -m ginstall install requests

Document dependencies

    pip freeze > req.txt

Run (using `graalpython` actually):

    python health.py

Converting into Java maven project:

    cp pomx.xml, src/

Docs
- https://www.graalvm.org/python/
- https://www.graalvm.org/reference-manual/python/
- https://www.graalvm.org/reference-manual/python/Packages/
