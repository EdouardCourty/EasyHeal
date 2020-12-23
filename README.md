# EasyHeal

### Introduction

This plugin adds two commands to your server.  
`/feed <player>` & `/heal <player>`  
`/feed` and `/heal` can also be used to target yourself.  

I wrote it for 1.16.4 and will update it for 1.17 when available.
I did not test it for previous verions, but it may work. I don't support it tho.

### Configuration

The configuration file is pretty straight-forward.  

```yaml
# Don't change this.
version: 1

# Available permissions (Self-exlpanatory):
# - easyheal.heal.self
# - easyheal.heal.others
# - easyheal.feed.self
# - easyheal.heal.others
# - easyheal.cooldown.bypass

# If set to false, no permission will be needed and the command will be accessible to everyone.

need_permission_to_heal_self: true
need_permission_to_heal_others: true
need_permission_to_feed_self: true
need_permission_to_feed_others: true

# 1 = 1 half heart (20 = full-life)
heal_amount: 20
feed_amount: 20

# If true, the player gets his saturation to be set to max when being fed.
give_saturation: true
saturation_amount: 20

# Cooldowns for players who don't have the easyheal.cooldown.bypass permission
use_feed_cooldown: true
# Cooldown duration (in seconds)
feed_cooldown_duration: 30

use_heal_cooldown: true
heal_cooldown_duration: 30

# Placeholders:
# - %playerName% Player's minecraft username
messages:
  no_permission: "§cYou cannot use this command."
  heal_received: "§aYou have been healed."
  heal_given: "§eYou healed %playerName%"
  feed_received: "§aYou have been fed."
  feed_given: "§eYou fed %playerName%"
  cooldown_not_finished: "§cToo soon ! Wait for it."
  player_not_found: "§cPlayer %playerName% not found"
```