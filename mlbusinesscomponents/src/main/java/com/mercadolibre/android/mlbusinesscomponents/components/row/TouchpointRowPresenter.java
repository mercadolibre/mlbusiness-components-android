package com.mercadolibre.android.mlbusinesscomponents.components.row;

import android.text.TextUtils;
import com.mercadolibre.android.mlbusinesscomponents.components.pickup.model.DescriptionItemsInterface;
import com.mercadolibre.android.mlbusinesscomponents.components.pill.model.PillResponseInterface;
import com.mercadolibre.android.mlbusinesscomponents.components.row.model.MainTitleTopInterface;
import com.mercadolibre.android.mlbusinesscomponents.components.row.model.TouchpointRowItemInterface;
import java.util.List;
import org.jetbrains.annotations.Nullable;

public class TouchpointRowPresenter {

    private static final String BLOCKED = "blocked";
    private static final String CLOSED = "closed";

    /* default */ void onBind(final TouchpointRowItemInterface rowItem, final TouchpointRowView view) {
        if (!rowItem.isValid()) {
            view.showSkeleton();
            return;
        }
        view.hideSkeleton();
        setImage(rowItem.getLeftImage(), view);
        setImageAccessory(rowItem.getLeftImageAccessory(), view);
        setRightBottomInfo(rowItem.getRightBottomInfo(), view);
        setMainTitleTop(rowItem.getMainTitleTop(), view);
        setTitle(rowItem.getMainTitle(), view);
        setSubtitle(rowItem.getMainSubtitle(), view);
        setStatusTextColor(rowItem.getMainTitleStatus(), view);
        setTopLabel(rowItem.getRightTopLabel(), view);
        setMainLabel(rowItem.getRightPrimaryLabel(), view);
        setRightLabel(rowItem.getRightSecondaryLabel(), view);
        setBottomLabel(rowItem.getRightMiddleLabel(), view);
        setDescriptionLabels(rowItem.getMainDescription(), view);
        setCharacteristicsLabels(rowItem.getMainCharacteristics(), view);
        setStatusDescription(rowItem.getStatusDescription(), view);
        setOnClick(rowItem.getLink(), view);
        setRightLabelStatus(rowItem.getRightLabelStatus(), view);
        setLeftImageStatus(rowItem.getLeftImageStatus(), view);
    }

    private void setLeftImageStatus(final String leftImageStatus, final TouchpointRowView view) {
        if (leftImageStatus == null || leftImageStatus.isEmpty()) {
            view.setLeftImageToDefaultStatus();
            return;
        }

        if (CLOSED.equals(leftImageStatus.toLowerCase())) {
            view.setLeftImageToClosedStatus();
        } else {
            view.setLeftImageToDefaultStatus();
        }
    }

    private void setRightLabelStatus(final String rightLabelStatus, final TouchpointRowView view) {
        if (rightLabelStatus == null || rightLabelStatus.isEmpty()) {
            view.setRightLabelsToDefaultStatus();
            return;
        }

        switch (rightLabelStatus.toLowerCase()) {
        case BLOCKED:
            view.setRightLabelsToBlockedStatus();
            break;
        case CLOSED:
            view.setRightContainerToClosedStatus();
            break;
        default:
            view.setRightLabelsToDefaultStatus();
        }
    }

    private void setImageAccessory(final String leftImageAccessory, final TouchpointRowView view) {
        if (TextUtils.isEmpty(leftImageAccessory)) {
            view.hideImageAccessory();
            return;
        }
        view.showImageAccessory(leftImageAccessory);
    }

    private void setDescriptionLabels(final List<DescriptionItemsInterface> mainDescription, final TouchpointRowView view) {
        if (mainDescription == null || mainDescription.isEmpty()) {
            view.hideDescriptionLabels();
            return;
        }
        view.showDescriptionLabels(mainDescription);
    }

    private void setCharacteristicsLabels(final List<DescriptionItemsInterface> mainDescription, final TouchpointRowView view) {
        if (mainDescription == null || mainDescription.isEmpty()) {
            view.hideCharacteristicsLabels();
            return;
        }
        view.showCharacteristicsLabels(mainDescription);
    }

    private void setStatusDescription(final List<DescriptionItemsInterface> statusDescription, final TouchpointRowView view) {
        if (statusDescription == null || statusDescription.isEmpty()) {
            view.hideStatusDescription();
            return;
        }
        view.showStatusDescription(statusDescription);
    }

    private void setOnClick(final String link, final TouchpointRowView view) {
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

    private void setRightBottomInfo(@Nullable final PillResponseInterface pill, final TouchpointRowView view) {
        if (pill == null) {
            view.hideRightBottomInfo();
            return;
        }

        view.showRightBottomInfo(pill);
    }

    private void setMainTitleTop(final MainTitleTopInterface mainTitle, final TouchpointRowView view) {
        if (mainTitle == null) {
            view.hideMainTitleTop();
            return;
        }

        view.showMainTitleTop(mainTitle);
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

    public void setStatusTextColor(final String status, final TouchpointRowView view) {
        if (TextUtils.isEmpty(status)) {
            return;
        }

        if (CLOSED.equals(status.toLowerCase())) {
            view.setTitleToClosedStatus();
        } else {
            view.setDefaultTitleClosedStatus();
        }
    }
}
