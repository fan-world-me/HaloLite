package dev.fanworld.halolite

import android.content.Intent
import android.os.Build
import android.service.quicksettings.Tile
import android.service.quicksettings.TileService

class HaloTileService : TileService() {

    override fun onStartListening() {
        val tile = qsTile ?: return
        tile.state = Tile.STATE_INACTIVE
        tile.label = "HaloLite"
        if (Build.VERSION.SDK_INT >= 29) {
            tile.subtitle = "${HaloPrefs.durationSec(this)} сек"
        }
        tile.updateTile()
    }

    override fun onClick() {
        startForegroundService(Intent(this, LightService::class.java))
    }
}
