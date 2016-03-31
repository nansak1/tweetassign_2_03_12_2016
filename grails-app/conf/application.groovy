/**
 * Created by nansak1 on 3/20/2016.
 */

grails.plugin.springsecurity.filterChain.chainMap = [

        //Stateless chain
        [
                pattern: '/api/**',
                filters: 'JOINED_FILTERS,-anonymousAuthenticationFilter,-exceptionTranslationFilter,-authenticationProcessingFilter,-securityContextPersistenceFilter,-rememberMeAuthenticationFilter'
        ]

]


grails.plugin.springsecurity.rest.token.storage.useGorm = true
grails.plugin.springsecurity.rest.token.storage.gorm.tokenDomainClassName = 'tweetassign_01.AuthenticationToken'
grails.plugin.springsecurity.rest.token.validation.useBearerToken = false
grails.plugin.springsecurity.rest.token.validation.headerName = 'X-Auth-Token'

grails.plugin.springsecurity.userLookup.userDomainClassName = 'tweetassign_01.Account'

grails.plugin.springsecurity.userLookup.usernamePropertyName = 'accountHandle'
//grails.plugin.springsecurity.password.algorithm ='MD5'
grails.plugin.springsecurity.userLookup.passwordPropertyName = 'accountPassword'

grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'tweetassign_01.UserRole'
grails.plugin.springsecurity.authority.className = 'tweetassign_01.Role'

grails.plugin.springsecurity.securityConfigType = 'InterceptUrlMap'
grails.plugin.springsecurity.interceptUrlMap = [
        [
                [pattern: '/api/accounts/**', access: ['ROLE_READ']]
        ]
]