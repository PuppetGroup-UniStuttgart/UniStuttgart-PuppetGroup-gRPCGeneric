# UniStuttgart-PuppetGroup-gRPCGeneric
Java project of Generic gRPC API

####Generic gRPC:

This is the gRPC implementation of a generic service in order to expose the main functionality of Puppet CM (Configuration Management) tooling.  
Thereby this gRPC API consumes valid Puppet Modules (shareable and usable unit of Puppet codes) available in puppetforge along with required SSH credentials to run the Modules on the Amazon EC2 machine.

####Generic proto  
Description of proto file being used in the gRPC API implementation of the Generic Service:

Service Name: GenericOps  
Method Name: create  

  Input to this method  
    
    credentials: The name of the pem file (key pair) of the EC2 instance which is required to SSH to it  
    bucketName: The name of the Amazon S3 bucket in which the above pem file is stored  
    username: Username of the Amazon EC2 instance in order to initiate session  
    publicIP: Public IP of the Amazon EC2 instance in order to initiate session  
    moduleName: Name of the Puppet Module which is to be deployed onto the EC2 instance (As available in the Puppet Forge)  
    installFile: Git link where install.pp file is present. This is required to run the Puppet Module
	
NOTE: Please maintain order of input to the method if you are manually invoking the API & naming convention

