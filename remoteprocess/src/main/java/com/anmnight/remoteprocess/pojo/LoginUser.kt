package com.anmnight.remoteprocess.pojo

/**
 * IsLoginFirst : false
 * AuthType : 1
 * AuthToken : NTFGQjMwN0IxNDhBQjAyOUFGRjI5QUI5NkFCMjg0MDJFNkQ0MDU5Mjg4MDE0ODFGOTY3Q0UwMjg2OTlENjBBMQ==
 * AuthenticationType : Basic
 * IsAuthenticated : true
 * Name :
 * SystemUserId : 18502938991
 * UserName :
 * UserCode : 18502938991
 * FriendlyName : null
 */
data class LoginUser(
        var authType: Int,
        var isIsLoginFirst: Boolean,
        var isIsAuthenticated: Boolean,
        var authToken: String,
        var authenticationType: String,
        var name: String,
        var systemUserId: String,
        var userName: String,
        var userCode: String,
        var friendlyName: String
)