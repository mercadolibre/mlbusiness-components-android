package com.mercadolibre.android.mlbusinesscomponents.components.row;

import android.text.TextUtils;
import java.util.List;
import org.jetbrains.annotations.Nullable;

public class TouchpointRowPresenter {

    /* default */ void onBind(final TouchpointRowItem rowItem, final TouchpointRowView view) {
        if (!rowItem.isValid()) {
            view.showSkeleton();
            return;
        }
        view.hideSkeleton();
        setImage(rowItem.getLeftImage(), view);
        //setPill(rowItem.getPill(), view);
        setTitle(rowItem.getMainTitle(), view);
        setSubtitle(rowItem.getMainSubtitle(), view);
        setTopLabel(rowItem.getRightTopLabel(), view);
        setMainLabel(rowItem.getRightPrimaryLabel(), view);
        setRightLabel(rowItem.getRightSecondaryLabel(), view);
        setBottomLabel(rowItem.getRightMiddleLabel(), view);
        setDescriptionLabels(rowItem.getMainDescription(), view);
        setOnClickListener(rowItem.getLink(), view);
    }

    private void setDescriptionLabels(final List<DescriptionItems> mainDescription, final TouchpointRowView view) {
        if (mainDescription == null || mainDescription.isEmpty()) {
            view.hideDescriptionLabels();
            return;
        }
        view.showDescriptionLabels(mainDescription);
    }

    private void setOnClickListener(final String link, final TouchpointRowView view) {
        if (!TextUtils.isEmpty(link)) {
            view.onClick(link);
        }
    }

    private void setImage(final String image, final TouchpointRowView view) {
        if (TextUtils.isEmpty(image)) {
            view.hideImage();
            return;
        }
        view.showImage(image);
    }

    private void setPill(@Nullable final PillResponse pill, final TouchpointRowView view) {
        /*if (pill == null) {
            view.hideLevel();
            return;
        }
        if (TextUtils.isEmpty(pill.getIcon())) {
            view.hideLevelIcon();
        } else {
            view.showLevelIcon(pill.getIcon());
            if (isValidPillFormat(pill)) {
                view.tintPillAsset(pill.getFormat().getTextColor());
            }
        }
        view.showLevelText(pill.getLabel(), pill.getFormat().getTextColor(),
            pill.getFormat().getBackgroundColor());*/
    }

    private void setTitle(final String title, final TouchpointRowView view) {
        if (TextUtils.isEmpty(title)) {
            view.hideTitle();
            return;
        }
        view.showTitle(title);
    }

    private void setSubtitle(final String subtitle, final TouchpointRowView view) {
        if (TextUtils.isEmpty(subtitle)) {
            view.hideSubtitle();
            return;
        }
        view.showSubtitle(subtitle);
    }

    private void setLocationLabel(final String distanceIcon, final String distanceLabel,
        final TouchpointRowView view) {
        /*if (!TextUtils.isEmpty(distanceLabel)) {
            view.showDistanceLabel(distanceLabel);
            if (!TextUtils.isEmpty(distanceIcon)) {
                view.showDistanceIcon(distanceIcon);
            }
        }*/
    }

    private void setTopLabel(final String topLabel, final TouchpointRowView view) {
        if (TextUtils.isEmpty(topLabel)) {
            view.hideTopLabel();
            return;
        }
        view.showTopLabel(topLabel);
    }

    private void setMainLabel(final String mainLabel, final TouchpointRowView view) {
        if (TextUtils.isEmpty(mainLabel)) {
            view.hideMainLabel();
            return;
        }
        view.showMainLabel(mainLabel);
    }

    private void setRightLabel(final String rightLabel, final TouchpointRowView view) {
        if (TextUtils.isEmpty(rightLabel)) {
            view.hideRightLabel();
            return;
        }
        view.showRightLabel(rightLabel);
    }

    private void setBottomLabel(final String bottomLabel, final TouchpointRowView view) {
        if (TextUtils.isEmpty(bottomLabel)) {
            view.hideBottomLabel();
            return;
        }
        view.showBottomLabel(bottomLabel);
    }

    private boolean isValidPillFormat(@Nullable final PillResponse pill) {
        return pill != null && pill.getFormat() != null &&
            !TextUtils.isEmpty(pill.getFormat().getTextColor());
    }
}
