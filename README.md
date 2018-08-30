1. To Begin, move the credentials file in the .aws folder to your home folder under the .aws folder (~/.aws/credentials). Add your access key and secret access key.

2. Open up the Java code and navigate to the top of the S3Upload class. Fill in the two variables bucketName and region. The bucketName must be unique across all buckets in AWS and abide by AWS naming standards.

3. Run the application. A new bucket will be provisoned and three html files will be added to this bucket. In addition, the bucket will be configured as a static website. Open up AWS and navigate to s3.

4.In order to access a particular web page, list the entire url of the s3 bucket and the web page at the end.

(http://healeytestbucketblue.s3-website.us-east-2.amazonaws.com/auto.html)

5.To run Junit Testing, provide the full url of the site you are testing for in the test class, as well as the text you are wishing to test, and run the test from the JUnit test package. Set the URL variable in the JUnit class.