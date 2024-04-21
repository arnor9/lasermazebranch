package org.dtu.lasermaze.view;

public enum TyleType {

    EMPTY ("tiles/empty.png"), LASER("tiles/laser.png"), MIRROR("tiles/mirror.png"), BEAMSPLITTER("tiles/beamSplitter.png"),CELLBLOCKER("tiles/cellBlocker.png"), CHECKPOINT("tiles/checkPoint.png"), TARGETRMIRROR("tiles/targetMirror.png");

    private String pictureFile;

    private TyleType(String pictureFile) {
        this.pictureFile = pictureFile;
    }

    public String getPictureFile() {
        return pictureFile;
    }
}