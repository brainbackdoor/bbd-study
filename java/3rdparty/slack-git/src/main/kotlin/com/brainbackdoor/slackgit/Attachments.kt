package com.brainbackdoor.slackgit

import java.lang.reflect.Field

data class Attachment(
        var text: String = "",
        var ref: String = "",
        var sha: String = ""
)