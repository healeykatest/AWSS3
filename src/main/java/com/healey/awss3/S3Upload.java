package com.healey.awss3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.UUID;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.services.s3.model.CreateBucketRequest;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.BucketWebsiteConfiguration;
import com.amazonaws.AmazonServiceException;



public class S3Upload {
    
    static String bucketName = "healeytestbucketblue";
    static String region = "us-east-2";
    

    public static void main(String[] args) throws IOException {

        AWSCredentials credentials = null;
        try {
            credentials = new ProfileCredentialsProvider().getCredentials();
        } catch (Exception e) {
            throw new AmazonClientException(
                    "Cannot load the credentials from the credential profiles file. " +
                    "Please make sure that your credentials file is at the correct " +
                    "location (~/.aws/credentials), and is in valid format.",
                    e);
        }

        AmazonS3 s3 = AmazonS3ClientBuilder.standard()
            .withCredentials(new AWSStaticCredentialsProvider(credentials))
            .withRegion(region)
            .build();
        
        String bucketPolicy = "{\n" +
            "    \"Version\": \"2012-10-17\",\n" +
            "    \"Id\": \"Policy1535652825140\",\n" +
            "    \"Statement\": [\n" +
            "        {\n" +
            "            \"Sid\": \"Stmt1535652821681\",\n" +
            "            \"Effect\": \"Allow\",\n" +
            "            \"Principal\": \"*\",\n" +
            "            \"Action\": \"s3:GetObject\",\n" +
            "            \"Resource\": \"arn:aws:s3:::"+bucketName+"/*\"\n" +
            "        }\n" +
            "    ]\n" +
            "}";
       
        String keyindex = "index.html";
        String keyerror = "error.html";
        String keyauto = "auto.html";
        
        s3.createBucket(bucketName);
        System.out.println("Success");
        

        try {
        
            System.out.println("Uploading a new object to S3 from a file\n");
            s3.putObject(new PutObjectRequest(bucketName, keyauto, createAutomationFile()));
            s3.putObject(new PutObjectRequest(bucketName, keyindex, createIndexFile()));
            s3.putObject(new PutObjectRequest(bucketName, keyerror, createErrorFile()));
            
            s3.setBucketWebsiteConfiguration(bucketName, new BucketWebsiteConfiguration("index.html", "error.html"));
            s3.setBucketPolicy(bucketName, bucketPolicy);
            

            
        } catch(Exception e){
            System.out.println("Error Occured");
        }
    }

    private static File createAutomationFile() throws IOException {
        File file = File.createTempFile("auto", ".html");
        file.deleteOnExit();

        Writer writer = new OutputStreamWriter(new FileOutputStream(file));
        writer.write("Automation for the people\n");
        writer.close();

        return file;
    }
    
    private static File createIndexFile() throws IOException {
        File file = File.createTempFile("index", ".html");
        file.deleteOnExit();

        Writer writer = new OutputStreamWriter(new FileOutputStream(file));
        writer.write("This is the index page\n");
        writer.close();

        return file;
    }
    
    private static File createErrorFile() throws IOException {
        File file = File.createTempFile("error", ".html");
        file.deleteOnExit();

        Writer writer = new OutputStreamWriter(new FileOutputStream(file));
        writer.write("This is the error page\n");
        writer.close();

        return file;
    }
    
    


}
