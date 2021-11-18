const config = require('dos-config').auth;
const request = require('request');
const express = require('express');
const router = express.Router();
const qs =  require('qs');

const claimMapper = require('./claim-mapper.js');

/* GET home page. */
router.get('/', function(req, res, next) {
  res.redirect(`https://${config.domain}/authorize` + '?' + qs.stringify({
    'response_type': 'code',
    'connection': 'google-oauth2',
    'state': req.query.return_url,
    'client_id': config.clientId,
    'redirect_uri': 'http://localhost:5000/authenticate/callback'
  }));
});

router.get('/callback', function getSingleSignOnUser(req, res, next) {
  const query = req.query;
  const uri = 'http://localhost:5000/authenticate/callback';
  const code = query.code;

  getSingleSignToken(code, uri, (err, resp, body) => {
    const rstr = JSON.parse(body);
    console.log('Goat', rstr.access_token);
    getUserProfile(rstr.access_token, (err, resp, body) => {
      if (err) return next(err);
      // console.log('RESP',resp);
      const state = Buffer.from(JSON.stringify(claimMapper(JSON.parse(body)))).toString('base64');

      // console.log(JSON.parse(body));
      res.redirect(`${req.query.state}?login=${state}`);
    });
  });
});

/**
 * Retrieves the Single Sign On Token from Auth0
 *
 * @param  {string} code        OAuth Authorization Code
 * @param  {string} redirectUri redirect_uri OAuth Paramter
 * @param  {Function} callback  Callback
 */
function getSingleSignToken(code, redirectUri, callback) {
  request.post({
    url: `https://${config.domain}/oauth/token`,
    form: {
      'code': code,
      'grant_type': 'authorization_code',
      'client_secret': config.clientSecret,
      'client_id': config.clientId,
      'redirect_uri': redirectUri
    }
  }, callback);
}

/**
 * Retrieves the User Profile from Auth0
 *
 * @param  {String} accessToken OAuth access token
 * @param  {Function} callback  Callback
 */
async function getUserProfile(accessToken, callback) {
  const url =
    `https://${config.domain}/userinfo?access_token=${accessToken}`;
  // const url = `https://www.googleapis.com/plus/v1/people/me?access_token=${accessToken}`;

  return request.get({ url }, callback);
  
}

module.exports = router;
