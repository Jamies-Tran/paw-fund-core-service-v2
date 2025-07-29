package com.paw.fund.core.service.domain.account.license;

import com.paw.fund.core.service.domain.account.license.content.AccountLicenseContent;
import com.paw.fund.core.service.domain.license.section.LicenseTemplateSection;
import com.paw.fund.core.service.domain.license.section.content.TemplateSectionContent;
import com.paw.fund.core.service.domain.license.template.LicenseTemplate;
import com.paw.fund.core.service.domain.media.info.MediaInfoList;
import lombok.Builder;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Builder
public record AccountLicenseDetail(
        Long accountLicenseId,
        Long licenseTemplateId,
        String title,
        String description,
        MediaInfoList media,
        List<LicenseSectionDetail> sections
) {

    @Builder
    public record LicenseSectionDetail(
            Long licenseTemplateSectionId,
            String sectionTitle,
            List<SectionContentDetail> contents
    ) {
        @Builder
        public record SectionContentDetail(
                Long accountLicenseContentId,
                Long templateSectionContentId,
                String sectionContent,
                List<ContentDetail> contents
        ) {
            @Builder
            public record ContentDetail(
                    Long accountLicenseContentId,
                    String licenseTemplateContent,
                    String licenseContent
            ) {

            }

            public static List<SectionContentDetail> from( List<AccountLicenseContent> contents) {
                Map<String, List<AccountLicenseContent>> contentMap = contents
                        .stream()
                        .collect(Collectors.groupingBy(content -> content.content().sectionContent()));
                return contentMap.entrySet()
                        .stream()
                        .map(entry -> {
                            List<ContentDetail> contentList = entry.getValue()
                                    .stream()
                                    .map(content1 -> ContentDetail.builder()
                                            .accountLicenseContentId(content1.accountLicenseContentId())
                                            .licenseContent(content1.content().licenseContent())
                                            .licenseTemplateContent(content1.content().licenseTemplateContent())
                                            .build())
                                    .toList();
                            return SectionContentDetail.builder()
                                    .sectionContent(entry.getKey())
                                    .contents(contentList)
                                    .build();
                        })
                        .toList();
            }

        }
        public static List<LicenseSectionDetail> from(List<LicenseTemplateSection> templateSections, List<AccountLicenseContent> contents) {
            return templateSections.stream()
                    .map(templateSection -> LicenseSectionDetail.builder()
                            .licenseTemplateSectionId(templateSection.licenseTemplateSectionId())
                            .sectionTitle(templateSection.sectionTitle())
                            .contents(SectionContentDetail.from(contents))
                            .build())
                    .toList();
        }
    }

    public static AccountLicenseDetail from(LicenseTemplate licenseTemplate, AccountLicense accountLicense) {
        return AccountLicenseDetail.builder()
                .accountLicenseId(accountLicense.accountLicenseId())
                .licenseTemplateId(licenseTemplate.licenseTemplateId())
                .title(licenseTemplate.title())
                .description(licenseTemplate.description())
                .media(accountLicense.media())
                .sections(LicenseSectionDetail.from(licenseTemplate.sections(), accountLicense.contents()))
                .build();
    }
}
