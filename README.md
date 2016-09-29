# medium-android-sdk-unofficial
[![](https://jitpack.io/v/KingsMentor/medium-android-sdk-unofficial.svg)](https://jitpack.io/#KingsMentor/medium-android-sdk-unofficial)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0)

Unoffical implementation of medium api for android . See https://github.com/Medium/medium-api-docs for medium official documentation

# Adding to your project.
	dependencies {
	        compile 'com.github.KingsMentor:medium-android-sdk-unofficial:-SNAPSHOT'
	}


#Building a Medium Client:

The official documentation currently covers these implementation:

- Acquire a short term authorization code
- Exchanging code for access token
- Refreshing access token
- Retriving user details
- Getting list of Publication from authorized user
- Getttig list of contributors in a publication
- Creating a Post
- Creating a Post in a Publication
- Uploading Image

First you must [register an application](https://medium.com/me/applications)on Medium. Then medium will supply you a *clientId* and a *clientSecret* with which you may access Medium’s API. Each integration should have its own *clientId* and *clientSecret*.

On registering the application on Medium, fill callback url field with : http://medium-unofficial.appspot.com

The *clientSecret* should be treated like a password and stored securely.
The first step is to *acquire a short term authorization code* .

**Acquiring a short term authorization code**

*requires* **MediumConnectionCallback** *to be implemented in the class*

To acquire a medium authorization code with the client library, call.
```
try {

            MediumClient mediumClient = new MediumClient.Builder(this, ApiHost.REQUEST_CODE)
                    .addScope(Scope.BASIC)
                    .addScope(Scope.PUBLICATION)
                    .addScope(Scope.POST)
                    .redirectUri(null)
                    .state("anySate")
                    .addConnectionCallback(this)
                    .clientID("YOUR-CLIENT-ID").build();

            mediumClient.connect();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("error :::: ", e.getMessage());
        }
```
this will  callback:

`onCodeRetrieved(Bundle bundle)` if oathorization was successful or

`onAccessDenied()` if user denials access or

`connectionFailed(MediumError mediumError)` if an error was encountered when making the request

the short authorization code is sent through a bundle in onCodeRetrieved(Bundle bundle)

        String code = bundle.getString(ClientConstant.connectionCode);
        String state = bundle.getString(ClientConstant.connectionState);

The following scope values are valid:

- basicProfile : Grants basic access to a user’s profile (not including their email).	
- listPublications	: Grants the ability to list publications related to the user.	
- publishPost	Grants : the ability to publish a post to the user’s profile.	
- uploadImage	Grants : the ability to upload an image for use within a Medium post.	

**Exchanging authorization code for access token**

*requires* **MediumConnectionCallback** *to be implemented in the class*

```
            MediumClient mediumClient = new MediumClient.Builder(this, ApiHost.ACCESS_TOKEN)
                    .code("SHORT-AUTHORIZATION-CODE")
                    .redirectUri(null)
                    .state("anySate")
                    .addConnectionCallback(this)
                    .clientSecret("YOUR-CLIENT-SECRET")
                    .clientID("YOUR-CLIENT-ID").build();

            mediumClient.connect();
  ```
  
  this returns authorization details through the call back:
  
  `onAccessTokenRefreshed(OauthDetails oauthDetails)`
  
  
**Refreshing access token with a refresh token**

*requires* **MediumConnectionCallback** *to be implemented in the class*

```
            MediumClient mediumClient = new MediumClient.Builder(this, ApiHost.REFRESH_TOKEN)
                    .refreshToken("YOUR-REFRESH-TOKEN")
                    .redirectUri(null)
                    .state("anySate")
                    .addConnectionCallback(this)
                    .clientSecret("YOUR-CLIENT-SECRET")
                    .clientID("YOUR-CLIENT-ID").build();

            mediumClient.connect();
  ```
  
  this returns authorization details through the call back:
  
  `onAccessTokenRefreshed(OauthDetails oauthDetails);`
  
  
**Retrieving User Details**

*requires* **MediumUserAuthCallback** *to be implemented in the class*

```
            MediumClient mediumClient = new MediumClient.Builder(this, ApiHost.ME)
                    .addConnectionCallback(this)
                    .accessToken("YOUR-ACCESS-TOKEN")
                    .clientSecret("YOUR-CLIENT-SECRET")
                    .clientID("YOUR-CLIENT-ID").build();

            mediumClient.connect();
  ```
  
  this returns user's details through the call back:
  
  `onUserDetailsRetrieved(MediumUser mediumUser); `


**Getting list of Publication from authorized user**

*requires* **PublicationCallback** *to be implemented in the class*

```
            MediumClient mediumClient = new MediumClient.Builder(this, ApiHost.PUBLICATION)
                    .addConnectionCallback(this)
                    .userId("AUTHORISE-USER-ID")
                    .accessToken("YOUR-ACCESS-TOKEN")
                    .clientSecret("YOUR-CLIENT-SECRET")
                    .clientID("YOUR-CLIENT-ID").build();

            mediumClient.connect();
  ```
  
  this returns user's publications through the call back:
  
  `onPublicationRetrieved(ArrayList<Publication> publications)`
  

**Getttig list of contributors in a publication**

*requires* **PublicationCallback** *to be implemented in the class*

```
            MediumClient mediumClient = new MediumClient.Builder(this, ApiHost.CONTRIBUTION)
                    .addConnectionCallback(this)
                    .userId("AUTHORISE-USER-ID")
                    .publicationId("PUBLICATION_-ID")
                    .accessToken("YOUR-ACCESS-TOKEN")
                    .clientSecret("YOUR-CLIENT-SECRET")
                    .clientID("YOUR-CLIENT-ID").build();

            mediumClient.connect();
  ```
  
  this returns publication contributors through the call back:
  
  `onReceivedContributors(ArrayList<Contributor> contributors);`
  
  
**Creating a Post and Creating a Post in a Publication**

*requires* **MediumPostPublicationCallback** *to be implemented in the class*


```         
             // start with creating a post object.
             Post post = new Post("title","content", PublishStatus.DRAFT, ContentFormat.html,"demo","medium unofficial");
             
            MediumClient mediumClient = new MediumClient.Builder(this, ApiHost.POST)
                    .addConnectionCallback(this)
                    .userId("AUTHORISE-USER-ID")
                    .publish(post)
                    .accessToken("YOUR-ACCESS-TOKEN")
                    .clientSecret("YOUR-CLIENT-SECRET")
                    .clientID("YOUR-CLIENT-ID").build();

            mediumClient.connect();
            
            // for creating post in a publication
            // note that publication id is added to the builder and ApiHost.PUBLICATION_POST is passed 
            
            MediumClient mediumClient = new MediumClient.Builder(this, ApiHost.PUBLICATION_POST)
                    .addConnectionCallback(this)
                    .userId("AUTHORISE-USER-ID")
                    .publicationId("PUBLICATION_-ID")
                    .publish(post)
                    .accessToken("YOUR-ACCESS-TOKEN")
                    .clientSecret("YOUR-CLIENT-SECRET")
                    .clientID("YOUR-CLIENT-ID").build();

            mediumClient.connect();
  ```
  
  this returns post object containing post creation details through the call back:
  
  `PostPublished(Post post);`
  
Publish status can be :
- Draft
- Unlisted
- Public

The contentFormat can be:
- html
- markdown


if the post was originally published elsewhere, add a canonicalUrl with:

`post.setCanonicalUrl("http://example.com");`



**Uploading Image**

*requires* **MediumPostPublicationCallback** *to be implemented in the class*

Most integrations will not need to use this resource. **Medium will automatically side-load any images specified by the src attribute on an `<img>` tag in post content when creating a post**. However, if you are building a desktop integration and have local image files that you wish to send, you may use the images endpoint.

```
            MediumClient mediumClient = new MediumClient.Builder(this, ApiHost.IMAGE_UPLOAD)
                    .addConnectionCallback(this)
                    .accessToken("YOUR-ACCESS-TOKEN")
                    .clientSecret("YOUR-CLIENT-SECRET")
                    .image(ImageContentType.PNG,"filename.png")
                    .clientID("YOUR-CLIENT-ID").build();

            mediumClient.connect();
  ```
  
  this returns Image object through the call back:
  
  `ImageUploaded(MediumImage mediumImage)`


#Handling Errors

If the right interface is not implemented when making the client call, it throws an exception giving information about the proper interface to implement.

Other client callback extends  ConnectionCallback

`public interface ConnectionCallback {
    abstract void connectionFailed(MediumError mediumError);
}`

This handles all unsuccessful response from medium endpoint.

message and error code can be gotten from `MediumError` object

***illustration***
```
            int errorCode = mediumError.getErrorCode();
            String errorMessage = mediumError.getErrorMessage();
            ErrorCodes errorCodes = EnumUtils.getErrorObjByCode(errorCode);
            switch (errorCodes) {
                        case ACCESS_TOKEN_REQUIRED:
                                    // do something
                                    break;
                        ///
                        }
```


## License

```
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```






