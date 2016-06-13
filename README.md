# medium-android-sdk-unofficial
Unoffical implementation of medium api for android . See https://github.com/Medium/medium-api-docs for medium official documentation

#Building a Medium Client:

The official documentation currently covers these implementation:

- Acquire a short term authorization code
- Exchanging code for access token
- Refreshing access token
- Retriving user details
- Getting list of Publication from oauthecated user
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
  



