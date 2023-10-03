import React from "react";
import { OauthSender } from "react-oauth-flow";

const GitHubLoginButton = () => {
  return (
    <OauthSender
      authorizeUrl="https://github.com/login/oauth/authorize"
      clientId="7154013dd77327365be6"
      redirectUri="http://localhost:3000/oauth2/redirect"
      render={({ url }) => <button onClick={() => window.location.href = url}>Login with GitHub</button>}
    />
  );
};

export default GitHubLoginButton;
