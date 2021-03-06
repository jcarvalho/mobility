/*
 * @(#)MinutesFile.java
 *
 * Copyright 2011 Instituto Superior Tecnico
 * Founding Authors: Susana Fernandes
 * 
 *      https://fenix-ashes.ist.utl.pt/
 * 
 *   This file is part of the Internal Mobility Module.
 *
 *   The Internal Mobility Module is free software: you can
 *   redistribute it and/or modify it under the terms of the GNU Lesser General
 *   Public License as published by the Free Software Foundation, either version 
 *   3 of the License, or (at your option) any later version.
 *
 *   The Internal Mobility  Module is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *   GNU Lesser General Public License for more details.
 *
 *   You should have received a copy of the GNU Lesser General Public License
 *   along with the Internal Mobility  Module. If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package module.mobility.domain;

import java.util.Map;

import javax.annotation.Nonnull;

import module.workflow.domain.AbstractWFDocsGroup;
import module.workflow.domain.ProcessDocumentMetaDataResolver;
import module.workflow.domain.ProcessFile;
import module.workflow.domain.WFDocsDefaultWriteGroup;
import module.workflow.domain.WorkflowProcess;
import pt.ist.bennu.core.util.ClassNameBundle;

@ClassNameBundle(bundle = "resources/MobilityResources")
/**
 * 
 * @author Susana Fernandes
 * 
 */
public class MinutesFile extends MinutesFile_Base {

    public MinutesFile(JobOffer jobOffer, String displayName, String filename, byte[] content) {
        super();
        init(displayName, filename, content);
        setProcess(jobOffer.getJobOfferProcess());
    }

    public MinutesFile(String displayName, String filename, byte[] content) {
        super();
        if (content != null) {
            init(displayName, filename, content);
        }
    }

    public static class MinutesFileMetadataResolver extends ProcessDocumentMetaDataResolver<ProcessFile> {

        private static final String WORKPLACE = "Local de trabalho";

        @Override
        public @Nonnull
        Class<? extends AbstractWFDocsGroup> getWriteGroupClass() {
            return WFDocsDefaultWriteGroup.class;
        }

        @Override
        public java.util.Map<String, String> getMetadataKeysAndValuesMap(ProcessFile processDocument) {
            MinutesFile minutesFile = (MinutesFile) processDocument;
            Map<String, String> metadataKeysAndValuesMap = super.getMetadataKeysAndValuesMap(processDocument);
            JobOfferProcess jobOfferProcess = (JobOfferProcess) minutesFile.getProcess();
            metadataKeysAndValuesMap.put(WORKPLACE, jobOfferProcess.getJobOffer().getWorkplace().getPresentationName());

            return metadataKeysAndValuesMap;

        }

    }

    @Override
    public ProcessDocumentMetaDataResolver<? extends ProcessFile> getMetaDataResolver() {
        return new MinutesFileMetadataResolver();
    }

    @Override
    public void setProcess(final WorkflowProcess process) {
        super.setProcess(process);
    }

}
